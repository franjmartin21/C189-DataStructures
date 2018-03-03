package c5;
/** Records the purchase and sale of stocks, and provides the capital gain or loss. */
public class StockLedger
{
    private QueueInterface<StockPurchase> ledger;
    public StockLedger()
    {
        ledger = new LinkedQueue<StockPurchase>();
    } // end default constructor
    /** Records a stock purchase in this ledger.
     @param sharesBought the number of shares purchased
     @param pricePerShare the price per share */
    public void buy(int sharesBought, double pricePerShare)
    {
        for (; sharesBought > 0; sharesBought--)
        {
            StockPurchase purchase = new StockPurchase(pricePerShare);
            ledger.enqueue(purchase);
        } // end for
    } // end buy
    /** Removes from this ledger any shares that were sold
     and computes the capital gain or loss.
     @param sharesSold the number of shares sold
     @param pricePerShare the price per share
     @return the capital gain (loss) */
    public double sell(int sharesSold, double pricePerShare)
    {
        double saleAmount = sharesSold * pricePerShare;
        double totalCost = 0;
        while (sharesSold > 0)
        {
            StockPurchase share = ledger.dequeue();
            double shareCost = share.getCostPerShare();
            totalCost = totalCost + shareCost;
            sharesSold--;
        } // end while
        return saleAmount - totalCost; // gain or loss
    } // end sell

    class StockPurchase<T>{

        private double costPerShare;

        private StockPurchase(double costPerShare){
            this.costPerShare = costPerShare;
        }

        public double getCostPerShare() {
            return costPerShare;
        }
    }

    public static void main(String[] args) {
        StockLedger myStocks = new StockLedger();
        myStocks.buy(20, 45); // buy 20 shares at $45
        myStocks.buy(20, 75); // buy 20 shares at $75
        double capGain = myStocks.sell(30, 65); // sell 30 shares at $65
        System.out.println(capGain);
    }
} // end StockLedger
