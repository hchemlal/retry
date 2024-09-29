package cm.hc1968.retrydemo.retrydemo.utils;

import cm.hc1968.retrydemo.retrydemo.models.SourceObject;
import cm.hc1968.retrydemo.retrydemo.models.TargetObject1;
import cm.hc1968.retrydemo.retrydemo.models.TargetObject2;

public class MappingUtils {
    public static TargetObject1 mapToTarget1(SourceObject source) {
        TargetObject1 target = new TargetObject1("dude1");
        target.setName(source.name());
        // Other mappings
        return target;
    }
    public static TargetObject2 mapToTarget2(SourceObject source) {
        TargetObject2 target = new TargetObject2("dude2");
        target.setName(source.name());
        // Other mappings
        return target;
    }


}