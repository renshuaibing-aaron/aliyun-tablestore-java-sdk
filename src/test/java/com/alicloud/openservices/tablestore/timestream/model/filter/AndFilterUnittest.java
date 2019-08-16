package com.alicloud.openservices.tablestore.timestream.model.filter;

import com.alicloud.openservices.tablestore.model.search.query.BoolQuery;
import com.alicloud.openservices.tablestore.model.search.query.Query;
import com.alicloud.openservices.tablestore.model.search.query.TermQuery;
import com.alicloud.openservices.tablestore.timestream.internal.TableMetaGenerator;
import com.alicloud.openservices.tablestore.timestream.internal.Utils;
import org.junit.Assert;
import org.junit.Test;

import static com.alicloud.openservices.tablestore.timestream.model.filter.FilterFactory.*;

public class AndFilterUnittest {
    @Test
    public void testBasic() {
        Tag tagFilter = Tag.equal("t1", "v1");
        Attribute attrFilter = Attribute.equal("a1", "v2");
        AndFilter condition = (AndFilter)(and(
                tagFilter,
                attrFilter
        ));
        Query query = condition.getQuery();
        Assert.assertTrue(query instanceof BoolQuery);
        Assert.assertEquals(((BoolQuery)query).getMustQueries().size(), 2);
        Assert.assertTrue(((BoolQuery)query).getMustQueries().get(0) instanceof TermQuery);
        Assert.assertEquals(((TermQuery)((BoolQuery)query).getMustQueries().get(0)).getFieldName(), TableMetaGenerator.CN_PK2);
        Assert.assertEquals(((TermQuery)((BoolQuery)query).getMustQueries().get(0)).getTerm().asString(), Utils.buildTagValue("t1", "v1"));
        Assert.assertTrue(((BoolQuery)query).getMustQueries().get(1) instanceof TermQuery);
        Assert.assertEquals(((TermQuery)((BoolQuery)query).getMustQueries().get(1)).getFieldName(), "a1");
        Assert.assertEquals(((TermQuery)((BoolQuery)query).getMustQueries().get(1)).getTerm().asString(), "v2");
    }
}
