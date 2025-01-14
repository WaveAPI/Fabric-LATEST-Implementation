package org.waveapi.ticker.tickers;

import org.waveapi.api.content.items.block.blockentities.DeltaTicking;
import org.waveapi.api.content.items.block.blockentities.WaveTileEntity;
import org.waveapi.ticker.DeltaTickManager;
import org.waveapi.ticker.DeltaTicker;

import java.util.ArrayList;
import java.util.List;

public class LimitedPerTick implements DeltaTicker {

    private record tickWrap(DeltaTicking ticking, long lastTick) {

    }
    private final List<tickWrap> ticking;
    private int i;
    private final int perTick;

    private long currentTick;

    public LimitedPerTick() {
        ticking = new ArrayList<>();
        i = 0;
        perTick = (int) DeltaTickManager.deltaTickingSettings.get("tickersPerTick");
    }

    @Override
    public void addTicking(DeltaTicking ticking) {
        this.ticking.add(new tickWrap(ticking, currentTick));
    }

    @Override
    public void tick() {
        currentTick++;
        for (int x = 0 ; x < perTick ; x++) {
            if (i >= ticking.size()) {
                i = 0;
                return;
            }
            tickWrap deltaTicking = this.ticking.get(i);
            if (deltaTicking.ticking instanceof WaveTileEntity && ((WaveTileEntity) deltaTicking.ticking).blockEntity.isRemoved()) {
                ticking.remove(i);
            }
            deltaTicking.ticking.tick((int) (currentTick - deltaTicking.lastTick));

            i++;
        }
    }

    @Override
    public void removeTicking(DeltaTicking ticking) {
        this.ticking.removeIf(tickWrap -> tickWrap.ticking == ticking);
    }
}
