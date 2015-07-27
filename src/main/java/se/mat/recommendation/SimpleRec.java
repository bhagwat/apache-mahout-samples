package se.mat.recommendation;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.List;

public class SimpleRec {
    public static void main(String[] args) throws Exception {
        //Loading data (ratings) from file
        DataModel model = new FileDataModel(Utils.getFile("data/ua.base"));
        //User pearson correlation for computing similarity
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        //Limiting the area of users for comparing to
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5, similarity, model);
        //Sending everything to recommendation engine
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        //Recommending 5 times for first user (ID:1)
        List<RecommendedItem> recommendations = recommender.recommend(2, 5);

        //Presenting recommendations
        for (RecommendedItem recommendedItem : recommendations) {
            System.out.println(recommendedItem);
        }
    }
}
