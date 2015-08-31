package beehive_web.models.entity;

import java.lang.reflect.Field;
import java.util.*;

import beehive_web.base.Json;

public class BaseEntity {
    public final String toString() {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for(int i = 0;i < fields.length;i++) {
                Field f = fields[i];
                f.setAccessible(true);
                m.put(f.getName(), f.get(this));
                f.setAccessible(false);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Json.encode(m);
    }
}
