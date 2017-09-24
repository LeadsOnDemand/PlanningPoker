namespace PlanningPoker.Api.Models
{
    public class ApiCallResult<T>
    {
        public T Data { get; set; }

        public string ErrorMessage { get; set; }

    }
}
