package icbm.classic.api.missiles.parts;

/**
 * Notes that this logic is just one step of many that missile will perform
 * as part of it's flight path.
 */
public interface IMissileFlightLogicStep extends MissileFlightLogic {

    /**
     * Gets the next step to run
     *
     * @return next step or null
     */
    MissileFlightLogic getNextStep();

    /**
     * Sets the next step, it is up to the current flight logic when this is applied
     *
     * @param logic to add as next step
     * @return self to allow for easier chaining
     */
    MissileFlightLogic setNextStep(MissileFlightLogic logic);

    default IMissileFlightLogicStep addStep(MissileFlightLogic logicStep) {
        if (getNextStep() == null) {
            setNextStep(logicStep);
        } else if (getNextStep() instanceof IMissileFlightLogicStep) {
            ((IMissileFlightLogicStep) getNextStep()).addStep(logicStep);
        } else {
            throw new IllegalArgumentException(this + "Next step is not an IMissileFlightLogicStep");
        }
        return this;
    }
}
