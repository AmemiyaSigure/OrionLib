package cx.rain.mc.orion.api;

import lombok.Getter;

public class VersionString {
    @Getter
    private int Major = 0;
    @Getter
    private int Minor = 0;
    @Getter
    private int Patch = 0;

    public VersionString(int major, int minor, int patch) {
        Major = major;
        Minor = minor;
        Patch = patch;
    }

    @Override
    public String toString() {
        return Major + "." + Minor + "." + Patch;
    }

    public boolean isTargetNewer(VersionString target) {
        if (target.Major < Major) {
            return false;
        } else if (target.Major > Major) {
            return true;
        } else {
            if (target.Minor < Minor) {
                return false;
            } else if (target.Minor > Minor) {
                return true;
            } else {
                if (target.Patch <= Patch) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
}
