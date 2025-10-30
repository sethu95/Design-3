class LRUCache {

    LRU lruCache;

    public LRUCache(int capacity) {
        lruCache = new LRU(capacity);
    }
    
    public int get(int key) {

        return lruCache.getValue(key);
        
    }
    
    public void put(int key, int value) {
        
        lruCache.addToHead(key, value);

    }
}

class LRU {

    ListNode head;
    ListNode tail;
    int capacity;
    Map<Integer, ListNode> cache;

    public LRU (int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);

        this.head.next = tail;
        this.tail.prev = head;
    }

    public void addToHead (int key, int value) {

        ListNode head1 = head;
        ListNode tail1 = tail;
        Map<Integer, ListNode> cache1 = cache;

        ListNode node = new ListNode(key, value);
        if (cache.containsKey(key)) {
            deleteNode(cache.get(key));
        } else if (cache.size() >= capacity) {
            deleteNode(this.tail.prev);
        }
        cache.put(key, node);
        ListNode currHead = this.head.next;
        this.head.next = node;
        node.next = currHead;
        currHead.prev = node;
        node.prev = this.head;

        head1 = head;
        tail1 = tail;
        cache1 = cache;
    }

    private void deleteNode (ListNode node) {

        ListNode prevNode = node.prev;
        ListNode nextNode = node.next;
        prevNode.next = node.next;
        nextNode.prev = node.prev;
        cache.remove(node.key);
        node.next = null;
        node.prev = null;
        

    }

    public int getValue (int key) {

        if (cache.containsKey(key)) {
            ListNode node = cache.get(key);

            deleteNode(node);
            addToHead(node.key, node.val);

            return node.val;
        } else {
            return -1;
        }
    }

}

class ListNode {
    int val;
    int key;
    ListNode prev;
    ListNode next;

    public ListNode (int key, int value) {
        this.key = key;
        this.val = value;
        this.prev = null;
        this.next = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
