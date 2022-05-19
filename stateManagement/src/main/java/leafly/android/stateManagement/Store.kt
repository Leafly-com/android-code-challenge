package leafly.android.stateManagement

typealias State = String

/**
 * An action is an operation that will modify the previous state and return a new state.
 */
interface Action<STATE> {
    fun run(previousState: STATE): STATE
}

/**
 * A callback that will be triggered whenever the state changes.
 */
interface StateListener<STATE> {
    fun onStateChanged(state: STATE)
}

/**
 * A store maintains & manages an instance of the state.
 */
class Store {
    fun registerListener(listener: StateListener<State>) {
        // TODO
    }

    fun updateState(action: Action<State>) {
        // TODO
    }
}