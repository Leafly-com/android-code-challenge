package leafly.android.stateManagement

import kotlinx.coroutines.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.random.Random

class StoreTest {
    @Test @RepeatedTest(9)
    fun updatesStateSequentially() {
        runBlocking {
            val store = Store()

            val states = mutableListOf<String>()
            store.registerListener { state ->
                states += state
            }

            awaitAll(
                async(Dispatchers.IO) {
                    store.updateState { state ->
                        Thread.sleep(Random.nextLong(0, 100))
                        state + "Hello"
                    }
                },
                async(Dispatchers.IO) {
                    store.updateState { state ->
                        Thread.sleep(Random.nextLong(0, 100))
                        state + "World"
                    }
                }
            )

            assert(states[0] == "Hello") { "Expected states[0] to be 'Hello', got ${states[0]} instead" }
            assert(states[1] == "HelloWorld") { "Expected states[1] to be 'HelloWorld', got ${states[1]} instead" }
        }
    }
}