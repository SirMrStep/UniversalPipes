package me.steep.universalpipes.utils;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;

public record WrappedDataWatcherBuilder(WrappedDataWatcher watcher) {

    public WrappedDataWatcherBuilder setObject(int index, Object value, Class<?> clazz) {
        this.watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(index, WrappedDataWatcher.Registry.get(clazz)), value);
        return this;
    }

    public WrappedDataWatcherBuilder setNoGravity() {
        return setObject(5, true, Byte.class);
    }

    public WrappedDataWatcherBuilder setGlowing() {
        return setObject(0, (byte) 0x40, Byte.class);
    }

    public WrappedDataWatcherBuilder setInvisibility() {
        return setObject(0, (byte) 0x20, Byte.class);
    }

    /**
     * Use only on ArmorStand!
     */
    public WrappedDataWatcherBuilder setMarker() {
        return setObject(15, (byte) 0x10, Byte.class);
    }

}
