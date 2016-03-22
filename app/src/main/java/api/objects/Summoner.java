package api.objects;

/**
 * Created by Oscar on 3/22/2016.
 */
public class Summoner {
    private String formattedName;
    private long id;
    private long revisionDate;
    private int profileIconId;
    private long level;

    public Summoner(String formattedName, long id, long revisionDate, int profileIconId, long level) {
        this.formattedName = formattedName;
        this.id = id;
        this.revisionDate = revisionDate;
        this.profileIconId = profileIconId;
        this.level = level;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
}
