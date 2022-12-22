package aoc.y2022.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryStateTest {

    Blueprint example1 = new Blueprint(1, new int[]{4, 0, 0}, new int[]{2, 0, 0}, new int[]{3, 14, 0}, new int[]{2, 0, 7});

    @Test
    void testExample1Flow() {
        FactoryState state = FactoryState.start();
        state = state.buildClayRobot(example1);
        assertEquals(new FactoryState(1, 0, 0, 0, 1, 1, 0, 0, 4), state);
        System.out.println(state);

        state = state.buildClayRobot(example1);
        assertEquals(new FactoryState(1, 2, 0, 0, 1, 2, 0, 0, 6), state);
        System.out.println(state);

        state = state.buildClayRobot(example1);
        assertEquals(new FactoryState(1, 6, 0, 0, 1, 3, 0, 0, 8), state);
        System.out.println(state);

        state = state.buildObsidianRobot(example1);
        assertEquals(new FactoryState(2, 4, 0, 0, 1, 3, 1, 0, 12), state);
        System.out.println(state);

        state = state.buildClayRobot(example1);
        assertEquals(new FactoryState(1, 7, 1, 0, 1, 4, 1, 0, 13), state);

        state = state.buildObsidianRobot(example1);
        assertEquals(new FactoryState(1, 5, 4, 0, 1, 4, 2, 0, 16), state);

        state = state.buildGeodeRobot(example1);
        assertEquals(new FactoryState(2, 17, 3, 0, 1, 4, 2, 1, 19), state);

        state = state.buildGeodeRobot(example1);
        assertEquals(new FactoryState(3, 29, 2, 3, 1, 4, 2, 2, 22), state);


    }

}