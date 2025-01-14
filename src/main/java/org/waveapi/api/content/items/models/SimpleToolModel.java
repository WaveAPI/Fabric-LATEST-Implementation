package org.waveapi.api.content.items.models;

import org.waveapi.api.content.items.WaveItem;
import org.waveapi.file.texture.Texture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SimpleToolModel extends ItemModel {

    private Texture texture;

    public SimpleToolModel(String texturePath) {
        this(new Texture(texturePath));
    }

    public SimpleToolModel(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void build(File pack, WaveItem item) {
        File model = new File(pack, "assets/" + item.getMod().name + "/models/item/" + item.getId() + ".json");

        model.getParentFile().mkdirs();

        try {
            String tPath = texture.get(pack, item.getMod(), "item/" + item.getId());
            Files.write(model.toPath(), ("{\n" +
                    "  \"parent\": \"minecraft:item/handheld\",\n" +
                    "  \"textures\": {\n" +
                    "    \"layer0\": \"" + tPath + "\"\n" +
                    "  }\n" +
                    "}").getBytes());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
