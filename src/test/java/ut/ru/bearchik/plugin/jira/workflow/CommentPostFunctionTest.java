package ut.ru.bearchik.plugin.jira.workflow;

import ru.bearchik.plugin.jira.workflow.CommentPostFunction;

import com.atlassian.jira.issue.MutableIssue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentPostFunctionTest
{
    public static final String MESSAGE = "my message";

    protected CommentPostFunction function;
    protected MutableIssue issue;

    @Before
    public void setup() {
        issue = mock(MutableIssue.class);
        when(issue.getDescription()).thenReturn("");

        function = new CommentPostFunction() {
            protected MutableIssue getIssue(Map transientVars) {
                return issue;
            }
        };
    }

    @Test
    public void testNullMessage() throws Exception
    {
        Map transientVars = Collections.emptyMap();
        function.execute(transientVars, null, null);

        verify(issue).setDescription("");
    }

    @Test
    public void testEmptyMessage() throws Exception
    {
        Map transientVars = new HashMap();
        transientVars.put("messageField", "");
        function.execute(transientVars, null, null);

        verify(issue).setDescription("");
    }

    @Test
    public void testValidMessage() throws Exception
    {
        Map transientVars = new HashMap();
        transientVars.put("messageField", MESSAGE);
        function.execute(transientVars, null, null);

        verify(issue).setDescription(MESSAGE);
    }

}