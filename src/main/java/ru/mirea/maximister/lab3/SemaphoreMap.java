package ru.mirea.maximister.lab3;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemaphoreMap<K, V> implements Map<K, V> {
    private final Map<K, V> map;
    private final Semaphore semaphore = new Semaphore(1);

    public SemaphoreMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public int size() {
        try {
            semaphore.acquire();
            try {
                return map.size();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            semaphore.acquire();
            try {
                return map.isEmpty();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            semaphore.acquire();
            try {
                return map.containsKey(key);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try {
            semaphore.acquire();
            try {
                return map.containsValue(value);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public V get(Object key) {
        try {
            semaphore.acquire();
            try {
                return map.get(key);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            semaphore.acquire();
            try {
                return map.put(key, value);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            semaphore.acquire();
            try {
                return map.remove(key);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        try {
            semaphore.acquire();
            try {
                map.putAll(m);
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public void clear() {
        try {
            semaphore.acquire();
            try {
                map.clear();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public Set<K> keySet() {
        try {
            semaphore.acquire();
            try {
                return map.keySet();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public Collection<V> values() {
        try {
            semaphore.acquire();
            try {
                return map.values();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        try {
            semaphore.acquire();
            try {
                return map.entrySet();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }
}
