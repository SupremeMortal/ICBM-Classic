package icbm.classic.world.item;

import icbm.classic.api.ICBMClassicAPI;
import icbm.classic.api.missiles.ICapabilityMissileStack;
import icbm.classic.api.missiles.IMissile;
import icbm.classic.api.missiles.IMissileAiming;
import icbm.classic.config.missile.ConfigMissile;
import icbm.classic.lib.LanguageUtility;
import icbm.classic.prefab.item.ItemICBMElectrical;
import icbm.classic.world.missile.logic.flight.DeadFlightLogic;
import icbm.classic.world.missile.logic.source.MissileSource;
import icbm.classic.world.missile.logic.source.cause.EntityCause;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityLivingBase;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static net.minecraft.world.item.UseAnim.BOW;

/**
 * Rocket Launcher
 *
 * @author Calclavia
 */

public class RocketLauncherItem extends ItemICBMElectrical {
    private static final int ENERGY = 1000000;
    private static final int FIRING_DELAY = 1000;
    private final Object2LongMap<UUID> clickTimePlayer = new Object2LongOpenHashMap<>();

    public RocketLauncherItem(Properties properties) {
        super(properties, "rocketLauncher");
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @OnlyIn(Dist.CLIENT)
            public float apply(ItemStack stack, @Nullable Level levelIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return BOW;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity shooter, int count) {
        if (shooter instanceof Player player) {
            if (this.getEnergy(stack) >= ENERGY || player.isCreative()) {
                // Check the player's inventory and look for missiles.
                for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) { // TODO: add ammo wheel to select missile to use
                    ItemStack inventoryStack = player.getInventory().getItem(slot);

                    ICapabilityMissileStack capabilityMissileStack = inventoryStack.getCapability(ICBMClassicAPI.MISSILE_STACK_CAPABILITY);
                    if (capabilityMissileStack != null) {
                        Level level = player.level();
                        if (!level.isClientSide()) {
                            IMissile missile = capabilityMissileStack.newMissile(level);
                            Entity missileEntity = missile.getMissileEntity();

                            if (missileEntity instanceof IMissileAiming) {
                                //Setup aiming and offset from player
                                ((IMissileAiming) missileEntity).initAimingPosition(player, 1, ConfigMissile.DIRECT_FLIGHT_SPEED);

                                //Init missile
                                missile.setFlightLogic(new DeadFlightLogic(ConfigMissile.HANDHELD_FUEL));
                                missile.setMissileSource(new MissileSource(level, missileEntity.position(), new EntityCause(player)));
                                missile.launch();

                                //Spawn entity into world
                                if (level.addFreshEntity(missileEntity)) {
                                    if (player.isShiftKeyDown()) { // TODO: allow missile to have control of when riding is allowed
                                        player.startRiding(missileEntity);
                                        player.setShiftKeyDown(false);
                                    } else if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == net.minecraft.world.item.Items.LEAD) {
                                        double x = shooter.getX();
                                        double y = shooter.getY();
                                        double z = shooter.getZ();

                                        for (LivingEntity victim : level.getEntitiesOfClass(LivingEntity.class, new AABB(
                                            x - 7.0D, y - 7.0D, z - 7.0D,
                                            x + 7.0D, y + 7.0D, z + 7.0D))
                                        ) {
                                            if (victim.getLeashHolder() == player) {
                                                //victim.setLeashHolder(missileEntity, true);
                                                victim.startRiding(missileEntity);
                                                break;
                                            }
                                        }
                                    }

                                    if (!player.isCreative()) {
                                        player.inventory.setInventorySlotContents(slot, capabilityMissileStack.consumeMissile());
                                        player.inventoryContainer.detectAndSendChanges();
                                        this.discharge(stack, ENERGY, true);
                                    }
                                } else {
                                    player.sendSystemMessage(MutableComponent.create(new TranslatableContents("item.icbmclassic:rocketLauncher.error.spawning", null, new Object[0])));
                                }
                            } else {
                                player.sendSystemMessage(MutableComponent.create(new TranslatableContents("item.icbmclassic:rocketLauncher.error.IMissileAiming", null, new Object[0])));
                            }

                            //Exit loop to prevent firing all missiles in inventory
                            return;
                        }

                        //Store last time player launched a rocket
                        clickTimePlayer.put(player.getUUID(), System.currentTimeMillis());
                    }
                }
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);

        long clickMs = System.currentTimeMillis();
        long clickTime = clickTimePlayer.getOrDefault(player.getUUID(), -1L);
        if (clickTime != -1) {
            if (clickMs - clickTime < FIRING_DELAY) {
                //TODO play weapon empty click audio to note the gun is reloading
                return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
            }
        }

//        player.swing(usedHand);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, Level level, List<String> list, ITooltipFlag flag) {
        final String key = "item.icbmclassic:rocketLauncher.info";
        String translation = LanguageUtility.getLocal(key);

        if (translation.contains("%s")) {
            String str = String.format(translation, String.valueOf(ConfigMain.ROCKET_LAUNCHER_TIER_FIRE_LIMIT));
            splitAdd(str, list, false, false);
        }

        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isCreative())
            list.add(new TextComponentTranslation("item.icbmclassic:rocketLauncher.info.creative").setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)).getFormattedText());
    }
}
