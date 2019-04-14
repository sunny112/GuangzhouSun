package amazonOA;

import java.util.*;

/********
 * public class Movie
 * {
 *
 * int movieId;.
 * float rating;
 * List<Movie> similarMovies
 * }
 *
 * 现在要求找到 k个和movie最相似 的movies。
 * 函数的signature大概是这样的：
 * public static List <Movie> getNearest(Movie movie, int numSimilar)。
 * 举个栗子：
 * m0 <--> m1, similarity 2-google 1point3acres
 * mo <--> m2, similarity 3
 * m1 <--> m3, similarity 4
 * m2 <--> m5, similaity 5
 * 如果要返回和mo最相似的movie, 那么应该返回 m5 (只有有一条路径从 m1到 m5, 并且 5是最大的）； 如果返回3个最相似的就返回 m2, m3， m5（顺序不重要）； 如果需要返回10个，但是相似的只有9个，那么就返回9个。
 * source movie本身不能在返回结果里面。
 * 可以的一个做法是 dfs + min-Heap(PriorityQueue)， 我们一直做dfs， 每次碰到一个新的movie，如果现在queue的size比 k小的话，直接加到queue里面； 如果新movie的rating比queue top movie的rating高的话， 把顶部movie
 * 踢出队列，加上这个新的。
 * update: 应该返回 m5 (只有有一条路径从 m1到 m5, 并且 5是最大的） 应该返回 m5 (只要有一条路径从 m1到 m5, 并且 5是最大的）
 */
class Movie {
    int id;
    int rate;
    List<Movie> neighbors;

    public Movie(int id, int rate, List<Movie> neighbors) {
        this.id = id;
        this.rate = rate;
        this.neighbors = neighbors;
    }
}
class Movie2 {
    private int id;
    float rate;
    List<Movie2> similarMovies;

    public List<Movie2> getSimilarMovies(){
        return this.similarMovies;
    }
    public Float getRating(){
        return this.rate;
    }

    public int getId(){

        return this.id;
    }
}
public class MovieSimilar {

        public  List<Movie> findHighestMovieRates(Movie movie, int k) {
            if (movie == null || k == 0)
                return null;
            List<Movie> res = new ArrayList<>();
            Set<Movie> visited = new HashSet<>();
            PriorityQueue<Movie> minHeap = new PriorityQueue<Movie>(k, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.rate - o2.rate;
                }
            });

            Queue<Movie> queue = new LinkedList<>();
            queue.offer(movie);
            visited.add(movie); //node: I missed, make sure the first node will not be added twice.
            while(!queue.isEmpty()) {
                Movie cur = queue.poll();

                if (minHeap.size() < k)
                    minHeap.offer(cur);
                else if (cur.rate > minHeap.peek().rate) {
                    minHeap.poll();
                    minHeap.offer(cur);
                }

                for (Movie neighbor : cur.neighbors) {
                    if (visited.add(neighbor)) //note: use set.add() to control each node is reached just once.
                        queue.add(neighbor);

                }
            }

            while(!minHeap.isEmpty()) {
                res.add(minHeap.poll());
            }

            return res;
        }

    /******
     *
     * 带有getter class Movie2
     */
    public List<Movie2> find(Movie2 m, int k){
        List<Movie2> res = new ArrayList<>();
        if(m==null || k<1) return res;
        //BFS
        Queue<Movie2> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Movie2> minHeap = new PriorityQueue<Movie2>(k, new Comparator<Movie2>(){
            @Override
            public int compare(Movie2 m1, Movie2 m2){
                if (m1.getRating() > m2.getRating()) return 1;
                else if(m1.getRating() < m2.getRating()) return -1;
                else return 0;
            }

        });

        q.offer(m);
        visited.add(m.getId());
        while(!q.isEmpty()){
            Movie2 tmp = q.poll();
            for(Movie2 movie: tmp.getSimilarMovies()){
                if(visited.add(movie.getId())){
                    if(minHeap.size()<k){
                        minHeap.offer(movie);
                    }else{
                        if(movie.getRating()>minHeap.peek().getRating()){
                            minHeap.poll();
                            minHeap.offer(movie);
                        }
                    }
                }
            }
        }
        //minHeap to arraylist
        while(!minHeap.isEmpty()){
            res.add(minHeap.poll());

        }

        Collections.reverse(res);
        return res;


    }
    public static void main(String[] args) {
            Movie[] testArray = new Movie[10];
            testArray[0] = new Movie(9, 6, new ArrayList<Movie>());
            testArray[1] = new Movie(1, 3, new ArrayList<Movie>());
            testArray[2] = new Movie(2, 8, new ArrayList<Movie>());
            testArray[3] = new Movie(3, 9, new ArrayList<Movie>());
            testArray[4] = new Movie(4, 7, new ArrayList<Movie>());
            testArray[5] = new Movie(5, 1, new ArrayList<Movie>());
            testArray[6] = new Movie(6, 2, new ArrayList<Movie>());
            testArray[7] = new Movie(7, 3, new ArrayList<Movie>());
            testArray[8] = new Movie(8, 5, new ArrayList<Movie>());
            testArray[9] = new Movie(9, 4, new ArrayList<Movie>());
            testArray[0].neighbors.add(testArray[4]);
            testArray[0].neighbors.add(testArray[2]);
            testArray[0].neighbors.add(testArray[3]);
            testArray[0].neighbors.add(testArray[1]);
            testArray[1].neighbors.add(testArray[4]);
            testArray[1].neighbors.add(testArray[2]);
            testArray[1].neighbors.add(testArray[9]);
            testArray[2].neighbors.add(testArray[3]);
            testArray[2].neighbors.add(testArray[6]);
            testArray[3].neighbors.add(testArray[7]);
            testArray[3].neighbors.add(testArray[8]);
            testArray[3].neighbors.add(testArray[1]);
            testArray[4].neighbors.add(testArray[3]);
            testArray[4].neighbors.add(testArray[5]);
            testArray[4].neighbors.add(testArray[7]);
            testArray[4].neighbors.add(testArray[8]);
            testArray[5].neighbors.add(testArray[2]);
            testArray[5].neighbors.add(testArray[6]);
            testArray[6].neighbors.add(testArray[9]);
            testArray[6].neighbors.add(testArray[0]);
            testArray[7].neighbors.add(testArray[0]);
            testArray[7].neighbors.add(testArray[1]);
            testArray[7].neighbors.add(testArray[2]);
            testArray[8].neighbors.add(testArray[4]);
            testArray[8].neighbors.add(testArray[3]);
            testArray[8].neighbors.add(testArray[7]);
            testArray[9].neighbors.add(testArray[0]);
            testArray[9].neighbors.add(testArray[2]);
            testArray[9].neighbors.add(testArray[6]);
            MovieSimilar sol = new MovieSimilar();
            List<Movie> res = sol.findHighestMovieRates(testArray[3], 3);

            for (Movie item : res) {
                System.out.print(" Movie id : " + item.id + " rate: " + item.rate + "\n");
            }
        }
}
