class Connection {
    constructor() {
        this.defaultUrl = "http://localhost:8081/api/";
    }

    async getStudents() {
        const response = await fetch(this.getUrl("enrollments"));
        this.checkResponse(response); // Check if response is okay
        return await response.json(); // Parse and return JSON data
    }

    async getStudentById(studentId) {
        const response = await fetch(this.getUrl(`students/${studentId}`));
        this.checkResponse(response);
        return await response.json();
    }

    async updateStudent(studentId, student) {
        const response = await fetch(this.getUrl(`students/${studentId}`), {
            method: "POST",
            headers: {
                "Content-Type": "application/json", // Specify content type
            },
            body: JSON.stringify(student), // Convert student object to JSON
        });
        this.checkResponse(response);
        return await response.json();
    }

    async getCourses() {
        const response = await fetch(this.getUrl("courses"));
        this.checkResponse(response);
        return await response.json();
    }

    async deleteEnrolment(id) {
        const response = await fetch(this.getUrl(`enrollments/${id}`), {
            method: "DELETE",
        });
        this.checkResponse(response);
    }

    async addStudent(student) {
        const response = await fetch(this.getUrl("students"), {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(student),
        });
        this.checkResponse(response);
        return await response.json();
    }

    async addEnrolment(studentId, selectedCourseId) {
        const response = await fetch(this.getUrl("enrollments"), {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ studentId: studentId, courseId: selectedCourseId }),
        });
        this.checkResponse(response);
    }

    getUrl(url) {
        return this.defaultUrl + url;
    }

    // Helper function to check response status
    checkResponse(response) {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`); // Throw an error for non-2xx responses
        }
    }
}

export default Connection;
