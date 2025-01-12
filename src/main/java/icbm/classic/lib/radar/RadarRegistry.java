package icbm.classic.lib.radar;

import com.google.common.collect.Lists;
import icbm.classic.ICBMClassic;
import icbm.classic.lib.transform.region.Cube;
import net.minecraft.client.telemetry.events.WorldUnloadEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

import java.util.HashMap;
import java.util.List;

/**
 * Map based system for tracking objects using a radar devices. Only works server side to prevent unwanted data from stacking up.
 * <p>
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 3/5/2016.
 */
public final class RadarRegistry {
    /**
     * Used only for event calls
     */
    public static final RadarRegistry INSTANCE = new RadarRegistry();
    //TODO add client side version for mini-map like systems
    //TODO add per machine tracking map that uses line of sight so hills can block it's view. (Visible Area Cache in other words)
    /**
     * Level id to radar map
     */
    private static final HashMap<DimensionType, RadarMap> RADAR_MAPS = new HashMap();

    /**
     * Adds an entity to the radar map
     *
     * @param entity - entity
     * @return true if added
     */
    public static boolean add(Entity entity) {
        if (entity != null && entity.isAlive() && !entity.level().isClientSide()) {
            RadarMap map = getRadarMapForLevel(entity.level());
            return map != null && getRadarMapForLevel(entity.level()).add(entity);
        }
        return false;
    }

    /**
     * Removes an entity from the radar map
     *
     * @param entity - entity
     * @return true if removed
     */
    public static boolean remove(Entity entity) {
        if (entity != null && entity.isAlive()) {
            RadarMap map = getRadarMapForLevel(entity.level());
            return map != null ? getRadarMapForLevel(entity.level()).remove(entity) : false;
        }
        return false;
    }

    /**
     * Gets a radar map for the world
     *
     * @param level - should be a valid world that is loaded and has a dim id
     * @return existing map, or new map if one does not exist
     */
    public static RadarMap getRadarMapForLevel(Level level) {
        if (level.isClientSide()) {
            if (ICBMClassic.runningAsDev) {
                ICBMClassic.logger().error("RadarRegistry: Radar data can not be requested client side.", new RuntimeException());
            }
            return null;
        }
        return getRadarMapForDim(level.dimensionType());
    }

    /**
     * Gets a radar map for a dimension
     *
     * @param dimID - unique dim id
     * @return existing mpa, or new map if one does not exist
     */
    public static RadarMap getRadarMapForDim(DimensionType dimID) {
        if (!RADAR_MAPS.containsKey(dimID)) {
            RadarMap map = new RadarMap(dimID);
            RADAR_MAPS.put(dimID, map);
            return map;
        }
        return RADAR_MAPS.get(dimID);
    }

    /**
     * Grabs all living radar objects within range
     *
     * @param level
     * @param x
     * @param y
     * @param z
     * @param distance
     * @return list, never null
     */
    public static List<Entity> getAllLivingObjectsWithin(Level level, double x, double y, double z, double distance) {
        return getAllLivingObjectsWithin(level, new Cube(x - distance, Math.max(0, y - distance), z - distance, x + distance, Math.min(ICBMClassic.MAP_HEIGHT, y + distance), z + distance));
    }

    /**
     * Grabs all living radar objects within range
     *
     * @param level
     * @param cube  - area to search for contacts
     * @return list, never null
     */
    public static List<Entity> getAllLivingObjectsWithin(Level level, Cube cube) {
        // TODO recode to take filter as input to reduce output list
        // TODO recode to use a consumer pattern, if so ignore filter and let consumer be the filter

        final List<Entity> list = Lists.<Entity>newArrayList();
        if (RADAR_MAPS.containsKey(level.dimensionType())) {
            final RadarMap map = getRadarMapForLevel(level);
            if (map != null) {
                final List<RadarEntity> objects = map.getRadarObjects(cube, true);
                for (RadarEntity object : objects) {
                    if (object != null && object.isValid()) {
                        Entity entity = ((RadarEntity) object).entity;
                        if (entity != null && entity.isAlive()) {
                            list.add(entity);
                        }
                    }
                }
            } else if (level.isClientSide() && ICBMClassic.runningAsDev) {
                ICBMClassic.logger().error("RadarRegistry: Radar data can not be requested client side.", new RuntimeException());
            }
        }
        return list;
    }

    @SubscribeEvent
    public void chunkUnload(ChunkEvent.Unload event) {
        DimensionType dim = event.getLevel().dimensionType();
        if (RADAR_MAPS.containsKey(dim)) {
            getRadarMapForDim(dim).remove(event.getChunk());
        }
    }

    @SubscribeEvent
    public void worldUpdateTick(TickEvent.LevelTickEvent event) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            DimensionType dim = event.level.dimensionType();
            if (RADAR_MAPS.containsKey(dim)) {
                RadarMap map = getRadarMapForDim(dim);
                if (map.isEmpty()) {
                    RADAR_MAPS.remove(dim);
                } else {
                    map.update();
                }
            }
        }
    }

    @SubscribeEvent
    public void worldUnload(LevelEvent.Unload event) {
        DimensionType dim = event.getLevel().dimensionType();
        if (RADAR_MAPS.containsKey(dim)) {
            getRadarMapForDim(dim).unloadAll();
            RADAR_MAPS.remove(dim);
        }
    }
}
