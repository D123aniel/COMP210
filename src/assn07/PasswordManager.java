package assn07;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = Math.abs(hash % _passwords.length);
        Account<K,V> newAccount = new Account<>(key, value);
        if(_passwords[index] == null || _passwords[index].getWebsite().equals(key)) {
            _passwords[index] = newAccount;
        } else {
            Account<K,V> current = _passwords[index];
            boolean changed = false;
            while (current.getNext() != null) {
                if(_passwords[index].getWebsite().equals(key)){
                    current = newAccount;
                    changed = true;
                    break;
                }
                current = current.getNext();
            }
            if(!changed){
                current.setNext(newAccount);
            }

        }

     }

    // TODO: get
    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = Math.abs(hash % _passwords.length);
        Account<K,V> current = _passwords[index];
        while (current != null) {
            if (current.getWebsite().equals(key)) {
                return current.getPassword();
            }
            current = current.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for (Account<K,V> index : _passwords) {
            if (index != null) {
                size++;
                Account<K,V> current = index;
                while (current.getNext() != null) {
                    size++;
                    current = current.getNext();
                }
            }
        }
        return size;
    }

    // TODO: keySet
    @Override
    //KeySet should be fine
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        for (Account<K,V> index : _passwords) {
            if (index != null) {
//                System.out.println(index.getWebsite());
                keys.add(index.getWebsite());
                Account<K,V> current = index;
                while (current.getNext() != null) {
//                    System.out.println("bro?");
                    current = current.getNext();
//                    System.out.println(current.getWebsite() + "Hi!");
                    keys.add(current.getWebsite());
                }
            }
        }
        return keys;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int hash = key.hashCode();
        int index = Math.abs(hash % _passwords.length);
        Account<K,V> current = _passwords[index];
        Account<K,V> previous = null;
        while (current != null) {
            if (current.getWebsite().equals(key)) {
                if (previous == null) {
                    _passwords[index] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return current.getPassword();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicate = new ArrayList<K>();
        for (Account<K,V> index : _passwords) {
            Account<K,V> current = index;
            while (current != null) {
                if (current.getPassword().equals(value)) {
                    duplicate.add(current.getWebsite());
                }
                current = current.getNext();
            }
        }
        return duplicate;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        if (enteredPassword.equals(MASTER_PASSWORD)) {
            return true;
        }
        return false;
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        if (length < 4) {
            targetStringLength = 4;
        }

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
