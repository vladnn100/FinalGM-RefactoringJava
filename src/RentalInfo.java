import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    
    String val[] = {"F001","F002","F003","F004"};
    String title[] = {"You've Got Mail","Matrix","Cars","Fast & Furious X"};
    String code[] = {"regular","regular","childrens","new"};
    
    for(int i=0;i<4;i++) {
    	movies.put(val[i], new Movie(title[i], code[i]));
    }
    
    /*movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));*/

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      /*if (movies.get(r.getMovieId()).getCode().equals("regular")) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.getMovieId()).getCode().equals("new")) {
        thisAmount = r.getDays() * 3;
      }
      if (movies.get(r.getMovieId()).getCode().equals("childrens")) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }*/
      
      switch(movies.get(r.getMovieId()).getCode()) {
      case "regular":
    	  thisAmount = 2;
          if (r.getDays() > 2) {
            thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
          }
        break;
      case "new":
    	  thisAmount = r.getDays() * 3;
        break;
      case "childrens":
    	  thisAmount = 1.5;
          if (r.getDays() > 3) {
            thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
          }
          break;
      default:
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getCode() == "new" && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
