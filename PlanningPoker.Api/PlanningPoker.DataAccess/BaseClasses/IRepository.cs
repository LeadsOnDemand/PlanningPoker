using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace PlanningPoker.DataAccess.BaseClasses
{
    public interface IRepository<T> where T : BaseEntity
    {
        Task<List<T>>  GetAllAsync();

        Task<List<T>> FindByAsync(Expression<Func<T, bool>> predicate);

        Task<T> GetByIdAsync(Int64 id);

        Task<int> InsertAsync(T entity);

        Task<int> UpdateAsync(T entity);

        Task<int> DeleteAsync(T entity);
    }
}
