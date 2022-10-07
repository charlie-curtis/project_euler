package helpers;

import java.util.AbstractMap;

public class Pair<K, V> extends AbstractMap.SimpleEntry<K, V> {
  public K first;
  public V second;

  public Pair(K first, V second) {
    super(first, second);
    this.first = first;
    this.second = second;
  }
}
