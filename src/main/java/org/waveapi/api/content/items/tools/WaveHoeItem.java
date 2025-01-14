package org.waveapi.api.content.items.tools;

import org.waveapi.api.WaveMod;
import org.waveapi.content.items.CustomItemWrap;
import org.waveapi.content.items.tool.CommonTool;
import org.waveapi.content.items.tool.CustomHoeWrap;
import org.waveapi.content.resources.TagHelper;

public class WaveHoeItem extends WaveCommonToolItem {

    public WaveHoeItem(String id, WaveMod mod, WaveToolMaterial material) {
        super(id, mod, material);
        speed = -2.0f;
        damage = -(int) (material.material.getAttackDamage());
    }

    @Override
    public void registerLocal() {
        TagHelper.addTag("fabric", "items/hoes", mod.name + ":" + id);
        this.base = new String[] {
                CustomHoeWrap.class.getName(),
                CommonTool.class.getName(),
                CustomItemWrap.class.getName()
        };
        baseRegister();
    }

    public WaveHoeItem setAttackDamage(int damage) {
        this.damage = damage;
        return this;
    }
    public WaveHoeItem setAttackSpeed(float speed) {
        this.speed = speed;
        return this;
    }

}
