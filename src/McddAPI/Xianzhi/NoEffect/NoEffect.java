package McddAPI.Xianzhi.NoEffect;

import McddAPI.Main;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;

import java.util.List;
import java.util.Random;

public class NoEffect {
    private Random r;

    public void onEffect() {
        if (!Main.instance.effect) {
            return;
        }
        this.r = new Random();
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();
        pm.addPacketListener(new PacketAdapter(Main.instance, ListenerPriority.NORMAL, PacketType.Play.Server.WORLD_PARTICLES) {
            public void onPacketSending(PacketEvent event) {
                final PacketContainer pc = event.getPacket();
                final List<EnumWrappers.Particle> particles = pc.getParticles().getValues();
                if (particles.contains(EnumWrappers.Particle.DAMAGE_INDICATOR)) {
                    final boolean isOn = Main.instance.getConfig().getBoolean("Effect");
                    final int max = Main.instance.getConfig().getInt("maxEffectNum");
                    final int min = Main.instance.getConfig().getInt("minEffectNum");
                    final int index = particles.indexOf(EnumWrappers.Particle.DAMAGE_INDICATOR);
                    if (isOn) {
                        final int rand = NoEffect.this.r.nextInt(max - min + 1) + min;
                        event.getPacket().getIntegers().write(index, rand);
                    }
                    else {
                        event.setCancelled(true);
                    }
                }
            }
        });
    }
}
