package com.jinhe.common.util.Tree;

import com.jinhe.common.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class TreeChildren {

    private static String ID = "id";
    private static String parentId = "parentId";
    private static String setId = "setId";
    private static String getId = "getId";
    private static String setParentId = "setParentId";
    private static String setChildren = "setChildren";
    private static String getParentId = "getParentId";

    public <T, TT> List CreateTree(List<T> objs, Class<TT> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<TT> list = new ArrayList<>();
        List<TT> listParent = new ArrayList<>();
        for (T item : objs) {
            TT target = clazz.getDeclaredConstructor().newInstance();
            Method[] srcMethods = item.getClass().getMethods();
            Method[] targetMethods = target.getClass().getMethods();
            for (Method m : srcMethods) {
                String srcName = m.getName();
                if (srcName.startsWith("get")) {
                    try {
                        Object result = m.invoke(item);
                        for (Method mm : targetMethods) {
                            String targetName = mm.getName();
                            if (targetName.startsWith("set") && targetName.substring(3, targetName.length())
                                    .equals(srcName.substring(3, srcName.length()))) {
                                mm.invoke(target, result);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
            list.add(target);
        }

        for (TT node : list) {
            boolean bool = list.stream().anyMatch(a -> {
                Object id = ReflectionUtils.invokeMethod(a, getId, null, null);
                Object parentId = ReflectionUtils.invokeMethod(node, getParentId, null, null);
                if (id.equals(parentId)) {
                    return true;
                } else {
                    return false;
                }
            });
            if (!bool) {
                listParent.add(node);
            }
        }
        for (TT t : listParent) {
            Object[] objects = new Object[1];
            List<TT> li = getChildren(clazz, list, clazz.getDeclaredMethod(getId).invoke(t).toString());
            if (li.size() > 0) {
                objects[0] = li;
                ReflectionUtils.invokeMethod(t, setChildren, new Class[]{java.util.List.class}, objects);
            }
        }
        return listParent;
    }

    public <TT> List<TT> getChildren(Class<TT> clazz, List<TT> list, String id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TT> listData = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(clazz.getDeclaredMethod(getParentId).invoke(list.get(i)))) {
                List<TT> li = getChildren(clazz, list, clazz.getDeclaredMethod(getId).invoke(list.get(i)).toString());
                if (li.size() > 0) {
                    ReflectionUtils.invokeMethod(list.get(i), setChildren, new Class[]{java.util.List.class},
                            new Object[]{li});
                }
                listData.add(list.get(i));
            }
        }
        return listData;
    }
}
