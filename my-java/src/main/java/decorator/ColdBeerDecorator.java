package decorator;

public class ColdBeerDecorator extends Beer {
    private Beer beer;
    public ColdBeerDecorator(Beer beer) {
        this.beer = beer;
    }
    @Override
    public String getName() {        
        return "Cold " + beer.getName() + "zimne ";
    }
}
