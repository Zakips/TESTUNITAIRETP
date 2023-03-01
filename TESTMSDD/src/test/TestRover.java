import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestRover {

    private Rover Rover;

    @BeforeEach
    public void setUpEach() {
        Rover = new Rover();
    }

    @Test
    public void InitRover() {

        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 0, Facing : N");
    }

    @Test
    public void MoveM() {

        Rover.command("M");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 1, Facing : N");

        Rover.command("MM");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 3, Facing : N");

        Rover.command("MMM");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 6, Facing : N");

    }
    @Test
    public void RotateR() {

        Rover.command("R");
        assertThat(Rover.getFacingDirection()).isEqualTo('E');

        Rover.command("RR");
        assertThat(Rover.getFacingDirection()).isEqualTo('W');

        Rover.command("RRR");
        assertThat(Rover.getFacingDirection()).isEqualTo('S');

    }

    @Test
    public void MoveToBorder() {

    	Rover.command("MMMMMMMMMM");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 6, Facing : N");
    }

    @Test
    public void MoveToOtherSide() {

        Rover.command("MMMMMMMMMM");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 6, Facing : N");

        Rover.command("M");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 1, Facing : N");
    }

    @Test
    public void GetBlockedByObstacle() {


        Rover.addObstacle(new Obstacle(0, 3)); // 0,2
        Rover.addObstacle(new Obstacle(3, 2)); // 2,2

        Rover.command("MM");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 4, Facing : N");

        Rover.command("M");
        assertThat(Rover.getPosition()).isEqualTo("X : 0, Y : 4, Facing : N");

        Rover.command("RMM");
        assertThat(Rover.getPosition()).isEqualTo("X : 4, Y : 4, Facing : E");

        Rover.command("M");
        assertThat(Rover.getPosition()).isEqualTo("X : 4, Y : 4, Facing : E");
    }
}
