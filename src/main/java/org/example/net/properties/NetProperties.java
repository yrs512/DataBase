package org.example.net.properties;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * 导入配置文件
 *
 * @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @date 2025-05-08 12:51
 */
public class NetProperties {
    private final Properties properties;
    public static final NetProperties DEFAULT;

    static {
        try (Reader reader = new FileReader("src/main/resources/net.properties")) {
            DEFAULT = new NetProperties(reader);
        } catch (IOException e) {
            throw new RuntimeException("打开配置文件失败", e);
        }
    }

    NetProperties(Reader reader) throws IOException {
        this.properties = new Properties();
        properties.load(reader);
    }

    NetProperties(InputStream inputStream) throws IOException {
        this.properties = new Properties();
        properties.load(inputStream);
    }

    public static enum PropertyName {
        CODE_FIELD("response.field.code"),
        MESSAGE_FIELD("response.field.message"),
        DATA_FIELD("response.field.data"),
        HOST("server.host"),
        PORT("server.port"),
        ;
        final String name;

        PropertyName(String name) {
            this.name = name;
        }
    }

    public String get(PropertyName propertyName) {
        return properties.getProperty(propertyName.name);
    }
}
