package decorator;

public class RunBeer {

    public static void main(String[] args) {
        Beer beer = new Beer();
        beer = new BeerDecorator(beer);
        beer = new ColdBeerDecorator(beer);
        System.out.println(beer.getName());
    }

}
