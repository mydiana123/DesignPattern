package cn.wjb114514.Command.Code;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemp {
    public static void main(String[] args) {
        // 设计模式 理念>实现 ==> 所以框架里的设计模式 和 规范的不太一样
        // 所有设计模式 几乎都在体现 里氏替换原则和ocp。即把继承关系转换为组合/结合，完成ocp。
        // 或者 管理一个子类型的集合，对外提供操作这个集合的方法，也可以实现ocp，即多加几个子类，都不会修改父类代码，还可以保证父类的业务逻辑
        // 我们通过聚合/组合一个 集合对象，完成了对父类的ocp原则~~~
        // 所以学设计模式 主要还是体会设计的6大基本原则，所以设计模式的灵魂还是六大基本原则。
        // 设计模式没有死记硬背的必要，需要我们在学习时理解其中体现的基本原则~~

    }
}
/*
@Nullable // query方法是JdbcTemplate类里的方法， 因此我们说JdbcTemplate就是命令调用者
// 逻辑为： Invoker.发布命令()[JdbcTemplate.query()] ==发布命令[query内部调用了一个execute()方法]==> QueryStatementCallBack[此类为实现了StatementCallBack(认为其为命令接口)的命令对象] ==命令对象执行命令==> 命令对象.执行命令()[action.doInStatement()]
jdbcTemplate.query() ===> this.execute(QueryStatementCallback action) ===> action.doInStatement();
这么看来，因为没有接受者的角色， 所以执行命令的操作 不能发生在命令对象内部，而是发生在命令发布者的内部，所以命令发布者是 知道命令接受者完成命令的细节的，这和我们传统的命令模式不同
但是这么做的好处是，提供了一个Command[StatementCallback]的接口， 实现子类有 {query/batch/update/executeStatementCallback}
所以在命令的调度方法 action.doInStatement()里，根据action的不同实现，可以完成不同功能，相当于对JdbcTemplate扩展了功能，又可以不修改源码。体现了ocp
public <T> T query(final String sql, final ResultSetExtractor<T> rse) throws DataAccessException {
    Assert.notNull(sql, "SQL must not be null");
    Assert.notNull(rse, "ResultSetExtractor must not be null");
    if (this.logger.isDebugEnabled()) {
        this.logger.debug("Executing SQL query [" + sql + "]");
    }

@FunctionalInterface // 此接口，有些类似命令模式的Command接口，用于调度接受者完成一个命令。
public interface StatementCallback<T> {
    @Nullable
    T doInStatement(Statement var1) throws SQLException, DataAccessException;
}
    // 此方法内部有一个内部类
    class QueryStatementCallback implements StatementCallback<T>, SqlProvider {
        QueryStatementCallback() {
        }

        @Nullable // 这个内部类有点类似ConcreteCommand类，是Command接口的实现子类，同时重写了execute()方法
        public T doInStatement(Statement stmt) throws SQLException {
            ResultSet rs = null;

            Object var3;
            try {
                rs = stmt.executeQuery(sql);
                var3 = rse.extractData(rs);
            } finally {
                JdbcUtils.closeResultSet(rs);
            }

            return var3;
        }

        public String getSql() {
            return sql;
        }
    }
    // 可以看到，，在jdbcTemplate的设计里，没有具体的receiver类，即命令对象本身 不仅定义了一个命令，还是命令 的接受者，自己完成了命令的业务逻辑。按理说 完成命令的业务逻辑，应该交给与命令绑定的receiver来做
    return this.execute((StatementCallback)(new QueryStatementCallback()));
}
@Nullable
public <T> T execute(StatementCallback<T> action) throws DataAccessException {
    Assert.notNull(action, "Callback object must not be null");
    Connection con = DataSourceUtils.getConnection(this.obtainDataSource());
    Statement stmt = null;

    Object var11;
    try {
        stmt = con.createStatement();
        this.applyStatementSettings(stmt);
        T result = action.doInStatement(stmt); // 在execute方法内部，我们说接受者 完成命令的模式为 接受者.执行命令() 这里变成了 命令对象.执行命令() 大同小异...
        this.handleWarnings(stmt);
        var11 = result;
    } catch (SQLException var9) {
        String sql = getSql(action);
        JdbcUtils.closeStatement(stmt);
        stmt = null;
        DataSourceUtils.releaseConnection(con, this.getDataSource());
        con = null;
        throw this.translateException("StatementCallback", sql, var9);
    } finally {
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, this.getDataSource());
    }

    return var11;
}
 */
