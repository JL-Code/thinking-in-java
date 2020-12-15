import lombok.Data;

/**
 * <p>创建时间: 2020/12/15 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;

    private T errors;
}
