/**
 * @author Sama Rahimian
 * Olga Veksler
 * 2210b
 * Objects of this class are stored in the dictionary.
 *
 */

public class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public K Key(){

        return key;
    }

    public V Value(){

        return value;
    }

    public void modifyValue(V v){

        this.value = v;
    }

}
