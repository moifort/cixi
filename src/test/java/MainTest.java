
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MainTest {
    private Main main = new Main();

    @Test
    public void result() {
        Assertions.assertThat(main.result()).isEqualTo(2);
    }
}