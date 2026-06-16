
import java.util.*;

class AuctionItem {
    int id;
    String name;
    PriorityQueue<Bid> bids;

    AuctionItem(int id, String name) {
        this.id = id;
        this.name = name;
        bids = new PriorityQueue<>((a, b) -> b.amount - a.amount);
    }

    void addBid(Bid bid) {
        bids.add(bid);
    }

    Bid getHighestBid() {
        if (bids.isEmpty()) {
            return null;
        }
        return bids.peek();
    }

    void showHistory() {
        ArrayList<Bid> list = new ArrayList<>(bids);
        list.sort((a, b) -> b.amount - a.amount);

        for (Bid bid : list) {
            System.out.println(bid.bidder.name + " : " + bid.amount);
        }
    }
}

class Bidder {
    int id;
    String name;

    Bidder(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Bid {
    Bidder bidder;
    int amount;

    Bid(Bidder bidder, int amount) {
        this.bidder = bidder;
        this.amount = amount;
    }
}

public class AuctionSystem {
    static ArrayList<AuctionItem> items = new ArrayList<>();
    static HashMap<Integer, Bidder> bidders = new HashMap<>();

    static AuctionItem searchById(int id) {
        for (AuctionItem item : items) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

    static AuctionItem searchByName(String name) {
        for (AuctionItem item : items) {
            if (item.name.equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Auction Item");
            System.out.println("2. Register Bidder");
            System.out.println("3. Place Bid");
            System.out.println("4. Display Highest Bid");
            System.out.println("5. End Auction");
            System.out.println("6. View Bidding History");
            System.out.println("7. Search Item");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Item ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Item Name: ");
                String name = sc.nextLine();

                items.add(new AuctionItem(id, name));

            } else if (choice == 2) {
                System.out.print("Enter Bidder ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Bidder Name: ");
                String name = sc.nextLine();

                bidders.put(id, new Bidder(id, name));

            } else if (choice == 3) {
                System.out.print("Enter Item ID: ");
                int itemId = sc.nextInt();

                System.out.print("Enter Bidder ID: ");
                int bidderId = sc.nextInt();

                System.out.print("Enter Bid Amount: ");
                int amount = sc.nextInt();

                AuctionItem item = searchById(itemId);
                Bidder bidder = bidders.get(bidderId);

                if (item != null && bidder != null) {
                    item.addBid(new Bid(bidder, amount));
                    System.out.println("Bid Placed Successfully");
                } else {
                    System.out.println("Invalid Item or Bidder");
                }

            } else if (choice == 4) {
                System.out.print("Enter Item ID: ");
                int itemId = sc.nextInt();

                AuctionItem item = searchById(itemId);

                if (item != null) {
                    Bid highest = item.getHighestBid();

                    if (highest != null) {
                        System.out.println("Highest Bid = " + highest.amount);
                        System.out.println("Bidder = " + highest.bidder.name);
                    } else {
                        System.out.println("No bids yet");
                    }
                }

            } else if (choice == 5) {
                System.out.print("Enter Item ID: ");
                int itemId = sc.nextInt();

                AuctionItem item = searchById(itemId);

                if (item != null) {
                    Bid winner = item.getHighestBid();

                    if (winner != null) {
                        System.out.println("Winner: " + winner.bidder.name);
                        System.out.println("Winning Bid: " + winner.amount);
                    } else {
                        System.out.println("No bids placed");
                    }
                }

            } else if (choice == 6) {
                System.out.print("Enter Item ID: ");
                int itemId = sc.nextInt();

                AuctionItem item = searchById(itemId);

                if (item != null) {
                    item.showHistory();
                }

            } else if (choice == 7) {
                sc.nextLine();
                System.out.print("Enter Item Name: ");
                String name = sc.nextLine();

                AuctionItem item = searchByName(name);

                if (item != null) {
                    System.out.println("Item ID: " + item.id);
                    System.out.println("Item Name: " + item.name);
                } else {
                    System.out.println("Item Not Found");
                }

            } else if (choice == 8) {
                break;
            }
        }
    }
}