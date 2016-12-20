using System;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.Text;


namespace HttpClientSample
{
    class Program
    {
        static HttpClient client = new HttpClient();

        static void ShowProduct(School product)
        {
            Console.WriteLine($"Name: {product.name}");
        }
            
        static async Task<School> CreateSchoolAsync(School school)
        {
            //converts object to json
            StringContent json = CreteJson(school);
            //Do post
            var response = await client.PostAsync("v1/schools", json);
            response.EnsureSuccessStatusCode();

            // Deserialize the Created product from the response body.
            return await response.Content.ReadAsAsync<School>(); ;
        }

        static async Task<School> GetSchoolAsync(string path)
        {
            School school = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                school = await response.Content.ReadAsAsync<School>();
            }
            return school;
        }

        static async Task<School> UpdateSchoolAsync(School school)
        {

            //converts object to json
            StringContent json = CreteJson(school);
            //Do post
            var response = await client.PutAsync($"v1/schools/{school.Id}", json);
            response.EnsureSuccessStatusCode();

            // Deserialize the updated product from the response body.
            return await response.Content.ReadAsAsync<School>(); ;
        }

        static async Task<HttpStatusCode> DeleteSchoolAsync(string id)
        {
            HttpResponseMessage response = await client.DeleteAsync($"v1/schools/{id}");
            return response.StatusCode;
        }

        static void Main()
        {
            RunAsync().Wait();
        }

        static async Task RunAsync()
        {
            client.BaseAddress = new Uri("http://localhost:3000/");
            
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //header woith token
            client.DefaultRequestHeaders.Add("Authorization", "Token token=pJzVJ9DZlhWxVotjRRRqoQtt");
 
            try
            {
                // Create a new product
                School school = new School { name = "TesteAPI" };

                school = await CreateSchoolAsync(school);
                Console.WriteLine($"Created School with id= {school.Id}");

                // Get the product
                school = await GetSchoolAsync("v1/schools/"+ school.Id);
                ShowProduct(school);

                // Update the product
                Console.WriteLine("Updating name...");
                school.name = "TesteUpdate";
                await UpdateSchoolAsync(school);

                // Get the updated product
                school = await GetSchoolAsync("v1/schools/" + school.Id);
                ShowProduct(school);

                // Delete the product
                var statusCode = await DeleteSchoolAsync(school.Id);
                Console.WriteLine($"Deleted (HTTP Status = {(int)statusCode})");

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }

            Console.ReadLine();
        }

        /// <summary>
        /// To Create Json
        /// </summary>
        /// <param name="school"></param>
        /// <returns></returns>
        static StringContent CreteJson(School school)
        {
            dynamic collectionWrapper = new
            {
                school = school
            };

            String output = JsonConvert.SerializeObject(collectionWrapper);

            return  new StringContent(output, Encoding.UTF8, "application/json");
        }

    }
}
