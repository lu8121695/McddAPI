package McddAPI.PlaceholderAPI;

import McddAPI.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimePapi extends PlaceholderExpansion {

    public TimePapi (Main plugin){
        Main.instance=plugin;
    }


    @Override
    public String getIdentifier() {
        return "time";
    }

    @Override
    public String getAuthor() {
        return Main.instance.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return Main.instance.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if (params!=null) {
            return dateDely(Integer.valueOf(params).intValue());
        }
        return null;
    }
    public String dateDely(int day) {
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat fmtrq = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        now.add(Calendar.DATE, day);
        String newDate = fmtrq.format(now.getTime());
        return newDate;
    }
}
