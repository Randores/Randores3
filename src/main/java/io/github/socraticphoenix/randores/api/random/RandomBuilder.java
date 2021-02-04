package io.github.socraticphoenix.randores.api.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class RandomBuilder {
    private Map<String, Object> results;
    private Random random;
    private String prevKey;

    public RandomBuilder(Random random) {
        this.results = new HashMap<>();
        this.random = random;
    }

    public Random random() {
        return this.random;
    }

    public Object get(String key) {
        return this.results.get(key);
    }

    public Number getNumber(String key) {
        Object obj = this.results.get(key);
        if (obj instanceof Number) {
            return (Number) obj;
        } else if (obj instanceof Boolean) {
            return (Boolean) obj ? 1 : 0;
        } else {
            return 0;
        }
    }

    public boolean getBoolean(String key) {
        Object obj = this.results.get(key);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else if (obj instanceof Number) {
            return ((Number) obj).doubleValue() != 0;
        } else {
            return false;
        }
    }

    public boolean[] getBooleanArr(String key) {
        return (boolean[]) this.results.get(key);
    }

    public int getInt(String key) {
        return this.getNumber(key).intValue();
    }

    public int[] getIntArr(String key) {
        return (int[]) this.results.get(key);
    }

    public double getDouble(String key) {
        return this.getNumber(key).doubleValue();
    }

    public double[] getDoubleArr(String key) {
        return (double[]) this.results.get(key);
    }

    public long getLong(String key) {
        return this.getNumber(key).longValue();
    }

    public long[] getLongArr(String key) {
        return (long[]) this.results.get(key);
    }

    public RandomBuilder percentChance(String key, double chance) {
        put(key, RandoresProbability.percentChance(chance, this.random));
        return this;
    }

    public RandomBuilder percentChance(String key, double chance, int size) {
        boolean[] arr = new boolean[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.percentChance(chance, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder randLong(String key) {
        put(key, this.random.nextLong());
        return this;
    }

    public RandomBuilder randLong(String key, int size) {
        long[] arr = new long[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.random.nextLong();
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder rand(String key, int upper) {
        put(key, this.random.nextInt(upper));
        return this;
    }

    public RandomBuilder rand(String key, int upper, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = this.random.nextInt(upper);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder rand(String key, double min, double max) {
        put(key, RandoresProbability.rand(min, max, this.random));
        return this;
    }

    public RandomBuilder rand(String key, double min, double max, int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.rand(min, max, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder expRand(String key, double b, double min, double max) {
        put(key, RandoresProbability.expRand(b, min, max, this.random));
        return this;
    }

    public RandomBuilder expRand(String key, double b, double min, double max, int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.expRand(b, min, max, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder oneSidedNormalRand(String key, double min, double stdev) {
        put(key, RandoresProbability.oneSidedNormalRand(min, stdev, this.random));
        return this;
    }

    public RandomBuilder oneSideNormalRand(String key, double mean, double stdev, int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.oneSidedNormalRand(mean, stdev, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder normalRand(String key, double mean, double stdev) {
        put(key, RandoresProbability.normalRand(mean, stdev, this.random));
        return this;
    }

    public RandomBuilder normalRand(String key, double mean, double stdev, int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.normalRand(mean, stdev, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder oneSidedInflectedNormalRand(String key, double min, double max, double stdev) {
        put(key, RandoresProbability.oneSidedInflectedNormalRand(min, max, stdev, this.random));
        return this;
    }

    public RandomBuilder oneSideInflectedNormalRand(String key, double min, double max, double stdev, int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.oneSidedInflectedNormalRand(min, max, stdev, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder inflectedNormalRand(String key, double min, double max, double mean, double stdev) {
        put(key, RandoresProbability.inflectedNormalRand(min, max, mean, stdev, this.random));
        return this;
    }

    public RandomBuilder inflectedNormalRand(String key, double min, double max, double mean, double stdev, int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandoresProbability.inflectedNormalRand(min, max, mean, stdev, this.random);
        }
        put(key, arr);
        return this;
    }

    public RandomBuilder clamp(int min, int max) {
        put(this.prevKey, RandoresProbability.clamp(this.getInt(this.prevKey), min, max));
        return this;
    }

    public RandomBuilder clamp(double min, double max) {
        put(this.prevKey, RandoresProbability.clamp(this.getDouble(this.prevKey), min, max));
        return this;
    }

    public RandomBuilder scale(double min, double max) {
        put(this.prevKey, RandoresProbability.scale(min, max, this.getDouble(this.prevKey)));
        return this;
    }

    public RandomBuilder op(Function<Number, Number> func) {
        put(this.prevKey, func.apply(this.getNumber(this.prevKey)));
        return this;
    }

    public RandomBuilder copy(String src, String dest) {
        put(dest, this.results.get(src));
        return this;
    }

    public RandomBuilder put(String key, Object x) {
        this.results.put(key, x);
        this.prevKey = key;
        return this;
    }

}
