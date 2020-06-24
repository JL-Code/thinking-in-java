package 泛型;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/24 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class PointByGeneric<T> {
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    private T x;
    private T y;
}
