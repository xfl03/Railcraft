/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.util.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.*;

/**
 * Created by CovertJaguar on 3/25/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class CollectionTools {
    private CollectionTools() {
    }

    @SafeVarargs
    public static <T> BiMap<Integer, T> createIndexedLookupTable(T... elements) {
        ImmutableBiMap.Builder<Integer, T> builder = ImmutableBiMap.builder();
        for (int i = 0; i <= elements.length; i++) {
            builder.put(i, elements[i]);
        }
        return builder.build();
    }

    public static <T> BiMap<Integer, T> createIndexedLookupTable(Iterable<T> elements) {
        ImmutableBiMap.Builder<Integer, T> builder = ImmutableBiMap.builder();
        Iterator<T> itr = elements.iterator();
        for (int i = 0; itr.hasNext(); i++) {
            builder.put(i, itr.next());
        }
        return builder.build();
    }

    public static <V> Map<StackKey, V> createItemStackMap() {
        return new HashMap<>();
    }

    public static Set<StackKey> createItemStackSet() {
        return new HashSet<>();
    }

    public static <T> boolean intersects(Collection<T> collection, T[] array) {
        return Arrays.stream(array).anyMatch(collection::contains);
    }
}
