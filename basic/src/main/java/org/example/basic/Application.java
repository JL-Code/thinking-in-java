package org.example.basic;

import org.example.basic.inner.NetworkMessage;
import org.example.basic.inner.QQMessage;
import org.example.basic.inner.address.IAddress;
import org.example.basic.泛型.Message;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/23 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Application {

    public static void main(String[] args) {
//        Message<Integer>[] messages = new Message[10];
//        System.out.println(messages.length);
//        QQMessage qqMessage = new QQMessage();
//        qqMessage.send();

        NetworkMessage message = new NetworkMessage(){
            @Override
            public void send() {
                System.out.println("网络消息2");
            }
        };

        message.send();

//        IAddress address = new IAddress() {
//            public String getProvince() {
//                return "重庆";
//            }
//            public String getCity() {
//                return "重庆";
//            }
//            public String getCounty() {
//                return "南岸区";
//            }
//        };
//        System.out.println(address.getCity());
    }
}
