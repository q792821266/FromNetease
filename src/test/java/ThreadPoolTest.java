import com.jerry.concurrent.ThreadPoolCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description: ThreadPoolTest <br>
 * date: 2021/8/28 6:02 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/8/286:02
 * @version: 1.0 <br>
 */
@Slf4j
public class ThreadPoolTest {

    /**
     * 提交15个任务，每个任务执行3秒  ，观察线程池的状况
     * @param threadPoolExecutor
     * @throws Exception
     */
    @Test
    public void test(ThreadPoolExecutor threadPoolExecutor) throws Exception{
        ThreadPoolCase threadPool = new ThreadPoolCase();
        threadPool.test();

    }
}
