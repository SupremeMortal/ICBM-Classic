package icbm.classic.world.missile.logic.source.cause;

import lombok.Getter;

public class MissileCauseType<T extends MissileCause> {

    private final Factory<T> factory;

    public MissileCauseType(Factory<T> factory) {
        this.factory = factory;
    }

    public T createCause() {
        return factory.create(this);
    }

    @FunctionalInterface
    public interface Factory<T extends MissileCause> {
        T create(MissileCauseType<T> type);
    }
}
