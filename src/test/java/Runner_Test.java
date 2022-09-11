import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Cucumber
@Execution(ExecutionMode.CONCURRENT)
public class Runner_Test {
}
