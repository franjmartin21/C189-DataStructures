package c15;

public class GuessingGame
{
    /**
    private DecisionTreeInterface<String> tree;
    public GuessingGame(String question, String noAnswer, String yesAnswer)
    {
        DecisionTree<String> no = new DecisionTree<String>(noAnswer);
        DecisionTree<String> yes = new DecisionTree<String>(yesAnswer);
        tree = new DecisionTree<String>(question, no, yes);
    } // end default constructor
    public void play()
    {
        tree.reset();
        while (!tree.isAnswer())
        {
            // ask current question
            System.out.println(tree.getCurrentData());
            if (Client.isUserResponseYes())
                tree.advanceToYes();
            else
                tree.advanceToNo();
        } // end while
        assert tree.isAnswer(); // Assertion: leaf is reached
        // make guess
        System.out.println("My guess is " + tree.getCurrentData() +". Am I right?");
        if (Client.isUserResponseYes())
            System.out.println("I win.");
        else
            learn();
    } // end play
    private void learn()
    {
     < Implementation left as a project in the next chapter. >
     . . .
    } // end learn
     */
} // end GuessingGame