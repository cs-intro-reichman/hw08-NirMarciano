/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
 class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
       if(size < maxSize) {
        //Tracks[size] = new Track (track.getTitle(), track.getArtist(), track.getDuration());
        tracks[size] = track;
        size++;
        return true;
       }
        return false;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder strB = new StringBuilder();
        for(int i = 0; i < size; i++){
        strB.append("\n" + tracks[i].toString());
        }
        return strB.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if(size != 0){
            tracks[size - 1] = null;
            size --;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += tracks[i].getDuration();
        }
        return total;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for (int i = 0; i < size; i++) {
            if(title.equals(tracks[i].getTitle())) return i;
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if(i < size && i > -1) {
            Track[] tempList = new Track[maxSize];
            for(int t = 0; t < size; t++) {
                tempList[t] = tracks[t];
            }
            tracks[i] = track;
            for(int j = (i + 1); j < (size + 1); j++) {
                tracks[j] = tempList[j - 1];
            }
            size++;
            return true;
        }
        return false;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if(size != 0){
            Track[] tempList = new Track[maxSize];
            for(int t = 0; t < size; t++){
                tempList[t] = tracks[t];
            }
            tracks[i] = null;
            for(int j = i + 1; j < size; j++){
                tracks[j - 1] = tempList[j];
            }
            size--;
        }   
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        int i = indexOf(title);
        if(size != 0){
            Track[] tempList = new Track[maxSize];
            for(int t = 0; t < size; t++){
                tempList[t] = tracks[t];
            }
            tracks[i] = null;
            for(int j = i + 1; j < size; j++){
                tracks[j - 1] = tempList[j];
            }
            size--;
        }     }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if(size != 0){
            Track[] tempList = new Track[maxSize];
            for(int t = 0; t < size; t++){
                tempList[t] = tracks[t];
            }
            tracks[size - 1] = null;
            for(int i = 1; i < size; i++){
                tracks[i - 1] = tempList[i];
            }
            size--;
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        int coSize = size + other.getSize();
        int n = 0;
        int i = size;
        if(coSize < maxSize) {
            while(i < coSize && n < other.getSize()){
                tracks[i] = other.getTrack(n);
                i++;
                n++;
                size++;
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        int min = tracks[start].getDuration();
        int index = -1;
        if(start < size && start > -1){
            index = start;
            for(int i = start + 1; i < size; i++){
                if(min > tracks[i].getDuration()){
                min = tracks[i].getDuration();
                index = i;
                }
            }
        }
        return index;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        int index;
        for(int j = 0; j < size; j++){
            index = minIndex(j);
            add(j, tracks[index]);
            remove(index + j);
        }
    }
}
