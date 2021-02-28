package ceph;


import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.twonote.rgwadmin4j.RgwAdmin;
import org.twonote.rgwadmin4j.RgwAdminBuilder;
import org.twonote.rgwadmin4j.model.User;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestDemo {

    public static void main(String[] args) {

//        创建连接
        String username = "testuser:swift";
        String password = "WCLGOEjVBtYD3k1UsNUhQ5b029xxnIXKT9ZB8Buy";
        String authUrl  = "http://192.168.2.33/auth/1.0";

        AccountConfig config = new AccountConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setAuthUrl(authUrl);
        config.setAuthenticationMethod(AuthenticationMethod.BASIC);
        Account account = new AccountFactory(config).createAccount();

//        创建一个容器
//        Container container = account.getContainer("my-new-container");
//        container.create();

//        创建一个对象
        Container container1 = account.getContainer("my-new-container");
//        upload-file
        StoredObject object1 = container1.getObject("foo1.txt");
//        location-file
        object1.uploadObject(new File("foo.txt"));

//        添加/更新对象元数据
        Container container2 = account.getContainer("my-new-container");
        StoredObject object2 = container2.getObject("foo.txt");
        Map<String, Object> metadata = new TreeMap<String, Object>();
        metadata.put("key", "value");
        object2.setMetadata(metadata);

//        容器list
        Collection<Container> containers = account.list();
        for (Container currentContainer : containers) {
            System.out.println(currentContainer.getName());
        }

//        列出一个容器的内容
        Container container3 = account.getContainer("my-new-container");
        Collection<StoredObject> objects = container3.list();
        for (StoredObject currentObject : objects) {
            System.out.println(currentObject.getName());
        }

        Container container = account.getContainer("my-new-container");
        StoredObject object = container.getObject("foo.txt");
        Map<String, Object> returnedMetadata = object.getMetadata();
        for (String name : returnedMetadata.keySet()) {
            System.out.println("META / "+name+": "+returnedMetadata.get(name));
        }

////        下载
//        Container container = account.getContainer("my-new-container");
//        StoredObject object = container.getObject("foo.txt");
//        object.downloadObject(new File("outfile.txt"));
//
////        删除对象
//        Container container = account.getContainer("my-new-container");
//        StoredObject object = container.getObject("foo.txt");
//        object.delete();
//
////        删除容器
//        Container container = account.getContainer("my-new-container");
//        container.delete();


    }
}


