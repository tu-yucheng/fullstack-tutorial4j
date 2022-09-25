package cn.tuyucheng.taketoday.boot.jsoncomponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserJsonDeserializer extends JsonDeserializer<User> {
    // @Override
    // public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    //     return null;
    //     // TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
    //     // TextNode favoriteColor = (TextNode) treeNode.get("favoriteColor");
    //     // return new User(Color.web(favoriteColor.asText()));
    // }


    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return null;
    }
}
