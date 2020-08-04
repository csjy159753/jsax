package com.jinhe.common.util.Tree;

import com.jinhe.common.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 树形结构工具类
 *
 * @param
 */
public class MapTree {
    public static String id="id";
    public static String parentId="parentId";
    public static String children="children";
    public static <T> List<ConcurrentHashMap<String,Object>> CreateTree(List<T> objs) {
        List<ConcurrentHashMap<String,Object>> list = new ArrayList<>();
        List<ConcurrentHashMap<String,Object>> listParent = new ArrayList<>();
        for (T item : objs) {
            ConcurrentHashMap<String,Object> treeNode = Mapper.entityToMap(item);
            list.add(treeNode);
        }
        for (ConcurrentHashMap<String,Object> nolde : list) {
             boolean bool = list.stream().anyMatch(a -> a.get(MapTree.id).equals(nolde.get(MapTree.parentId)));
            if (!bool) {
                listParent.add(nolde);
            }
        }
        for (ConcurrentHashMap<String,Object> t : listParent) {
            t.put(MapTree.children,getChildren(list,   t));
        }
        return listParent;
    }

    public static <T> List<ConcurrentHashMap<String,Object>> getChildren(List<ConcurrentHashMap<String,Object>> list,ConcurrentHashMap<String,Object> t) {
        List<ConcurrentHashMap<String,Object>> listData = new ArrayList<>();
        for (ConcurrentHashMap<String,Object> node : list) {
            if (t.get(MapTree.id).equals(node.get(MapTree.parentId))) {
                node.put(MapTree.children,getChildren(list, node));
                listData.add(node);
            }
        }
        return listData;
    }
}
