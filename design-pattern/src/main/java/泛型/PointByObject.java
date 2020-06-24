package 泛型;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/24 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class PointByObject {
    public Object getX() {
        return x;
    }

    public void setX(Object x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }

    private Object x;
    private Object y;

    @Override
    public String toString() {
        return "PointByObject{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
