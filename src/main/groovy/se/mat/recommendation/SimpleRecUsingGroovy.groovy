package se.mat.recommendation

import groovy.util.logging.Log
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity
import org.apache.mahout.cf.taste.model.DataModel
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood
import org.apache.mahout.cf.taste.recommender.Recommender
import org.apache.mahout.cf.taste.similarity.UserSimilarity

@Log
class SimpleRecUsingGroovy {
    public static void main(String[] args) {
        log.info "Loading data (ratings) from file"
        DataModel model = new FileDataModel(getFile("data/ua.base"))
        log.info "User pearson correlation for computing similarity"
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model)
        log.info "Limiting the area of users for comparing to"
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5, similarity, model)
        log.info "Sending everything to recommendation engine"
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity)
        log.info "Recommending 5 times for first user (ID:1)"
        log.info "Presenting recommendations"
        recommender.recommend(2, 5).each { recommendedItem ->
            log.info ">>>>>>>>>>>>>>" + recommendedItem
        }
    }

    public static File getFile(String filePath) {
        return new File(SimpleRecUsingGroovy.class.classLoader.getResource(filePath).toURI());
    }
}
