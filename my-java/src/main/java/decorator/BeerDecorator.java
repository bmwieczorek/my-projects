package decorator;

public class BeerDecorator extends Beer {
    Beer beer;
    public BeerDecorator(Beer beer) {
        this.beer = beer;
    }
    @Override
    public String getName() {
        return "Nice " + beer.getName() + "is ";
    }
}
