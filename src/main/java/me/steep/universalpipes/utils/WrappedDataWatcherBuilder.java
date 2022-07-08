package me.steep.universalpipes.utils;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;

public record WrappedDataWatcherBuilder(WrappedDataWatcher watcher) {

    public WrappedDataWatcherBuilder setObject(int index, Object value, Class<?> clazz) {
        watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(index, WrappedDataWatcher.Registry.get(clazz)), value);
        return this;
    }

}
